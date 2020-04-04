package cigo.app;

import java.util.List;
import java.util.Map;
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

import cigo.analysis.persistence.services.IAnagDependentService;

@RestController
public class CigoController {

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
		return mav;
	}
	
	@RequestMapping("/ranking")
	public ModelAndView ranking() {
		ModelAndView mav = new ModelAndView("ranking");
		mav.addObject("dependentList", anagDependentService.findRanking());
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
