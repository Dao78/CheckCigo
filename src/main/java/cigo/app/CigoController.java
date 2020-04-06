package cigo.app;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cigo.analysis.fileutilities.parsers.CigoDependentParser;
import cigo.analysis.fileutilities.parsers.IDependentFileParser;
import cigo.analysis.fileutilities.parsers.element.AnagDependent_FileElement;
import cigo.analysis.fileutilities.parsers.element.CigoDependent_FileElement;
import cigo.analysis.persistence.RankingComparator;
import cigo.analysis.persistence.repositories.AnagDependentFromFileRepository;
import cigo.analysis.persistence.services.IAnagDependentService;

@RestController
public class CigoController {
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	@Autowired
	private IAnagDependentService anagDependentService;

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}

	//@RequestMapping("/")
	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("index");
	}

	@RequestMapping("/showWeek")
	public ModelAndView showWeek(@RequestParam(required = true) String weekFile) {
		ModelAndView mav = new ModelAndView("week");
		mav.addObject("week", weekFile);
		mav.addObject("dependentList", anagDependentService.findDependentInCigo(weekFile));
		return mav;
	}


	@RequestMapping("/recapWeek")
	public ModelAndView weekRecap(@RequestParam(required = true) String weekFile) {
		ModelAndView mav = new ModelAndView("weekRecap");
		List<Dependent> depList = anagDependentService.findDependentInCigo(weekFile);
		Map<String, List<Dependent>> result = depList.stream().collect(Collectors.groupingBy(Dependent::getOfficeInfo,	Collectors.mapping(Function.identity(), Collectors.toList())));
		mav.addObject("dependentListUffici",    result.get("UFFICI"));
		mav.addObject("dependentListOperativo", result.get("OPERATIVO"));

		addTotPecentage(mav, result);

		return mav;
	}

	private void addTotPecentage(ModelAndView mav, Map<String, List<Dependent>> result) {
		int totUffici = result.get("UFFICI").size();
		int totOperativo = result.get("OPERATIVO").size();
		mav.addObject("totUfficiInCigo", totUffici);
		mav.addObject("totOperativoInCigo", totOperativo);
		mav.addObject("percUfficiInCigo", df2.format(totUffici*100.0/(totUffici + totOperativo))+"%");
		mav.addObject("percOperativoInCigo", df2.format(totOperativo*100.0/(totUffici + totOperativo))+"%");
	}

	@RequestMapping("/ranking")
	public ModelAndView ranking() {
		ModelAndView mav = new ModelAndView("ranking");
		final List<AnagDependent_FileElement> anagDependentList = AnagDependentFromFileRepository.getInstance().getAnagDependentList();
		anagDependentList.forEach(AnagDependent_FileElement::reset);
		AtomicInteger totUfficiInCigoAtomic    = new AtomicInteger(0);
		AtomicInteger totOperativoInCigoAtomic = new AtomicInteger(0);
		List<String> allCigoFiles = IDependentFileParser.getAllCigoFiles();
		allCigoFiles.stream().forEach(f -> {
			final List<CigoDependent_FileElement> cigoDependentList = new CigoDependentParser().readFile(IFileConstants.FILE_FOLDER+f);
			cigoDependentList.stream().forEach(d -> {
				AnagDependent_FileElement dep = anagDependentService.selectAnagraficData(anagDependentList, d);
				if (dep != null) {
					if (dep.getUffici_piazzale().equals(IFileConstants.UFFICI)) {
						totUfficiInCigoAtomic.incrementAndGet();
					} else {
						totOperativoInCigoAtomic.incrementAndGet();
					}
					dep.addWeek();
				} else {
					System.err.println("Dependent not found in anag file " + d);
				}
			});
		});

		List<Dependent> depList = anagDependentList.stream().map(Dependent::new).collect(Collectors.toList());
		Collections.sort(depList, new RankingComparator());
		mav.addObject("dependentList", depList);

		int totAnagUffici      = AnagDependentFromFileRepository.getInstance().getDependentiUfficio().size();
		int totAnagOperativo   = AnagDependentFromFileRepository.getInstance().getDependentOperativo().size();

		int totUfficiInCigo    = totUfficiInCigoAtomic.get();
		int totOperativoInCigo = totOperativoInCigoAtomic.get();
		mav.addObject("totUfficiInCigo",     totUfficiInCigo);
		mav.addObject("totOperativoInCigo",  totOperativoInCigo);
		mav.addObject("percUfficiInCigo",    df2.format(totUfficiInCigo   *100.0/(totUfficiInCigo + totOperativoInCigo))+"%");
		mav.addObject("percOperativoInCigo", df2.format(totOperativoInCigo*100.0/(totUfficiInCigo + totOperativoInCigo))+"%");

		mav.addObject("totAnagUffici",    totAnagUffici);
		mav.addObject("totAnagOperativo", totAnagOperativo);

		AtomicInteger totUfficiSalviAtomic    = new AtomicInteger(0);
		AtomicInteger totOperativoSalviAtomic = new AtomicInteger(0);
		anagDependentList.stream().forEach(d -> {
			if (d.getWeekCounter() == 0) {
				if (d.isUffici()) {
					totUfficiSalviAtomic.incrementAndGet();
				} else {
					totOperativoSalviAtomic.incrementAndGet();
				}
			}
		});

		int totUfficiSalvi = totUfficiSalviAtomic.get();
		int totOperativoSalvi = totOperativoSalviAtomic.get();
		mav.addObject("totAnagUfficiSalvi",    totUfficiSalvi);
		mav.addObject("totAnagOperativoSalvi", totOperativoSalvi);
		mav.addObject("percUfficiSalvi",       df2.format(totUfficiSalvi   *100.0/totAnagUffici)+"%");
		mav.addObject("percOperativoSalvi",    df2.format(totOperativoSalvi*100.0/totAnagOperativo)+"%");
		return mav;
	}

	/*@RequestMapping("/firstWeekPaged")
	public String listBooks(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Page<AnagDependent> dependentPage = anagDependentService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("dependentPageList", dependentPage);

		int totalPages = dependentPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "firstWeek";
	}*/
}
