package cigo.app;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cigo.analysis.persistence.AnagDependent;
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

	@RequestMapping("/firstWeek")
	public ModelAndView firstWeek() {
		ModelAndView mav = new ModelAndView("firstWeek");
		mav.addObject("dependentList", anagDependentService.findDependentInCigo(IFileConstants.FIRST_WEEK_DEPENDENT_LIST));
		return mav;
	}

	@RequestMapping("/firstWeekRecap")
	public ModelAndView firstWeekRecap() {
		ModelAndView mav = new ModelAndView("firstWeekRecap");
		List<Dependent> depList = anagDependentService.findDependentInCigo(IFileConstants.FIRST_WEEK_DEPENDENT_LIST);
		Map<String, List<Dependent>> result = depList.stream().collect(
				Collectors.groupingBy(Dependent::getOfficeInfo,	Collectors.mapping(Function.identity(), Collectors.toList())));
		mav.addObject("dependentList", anagDependentService.findDependentInCigo(IFileConstants.FIRST_WEEK_DEPENDENT_LIST));
		return mav;
	}

	@RequestMapping("/firstWeekPaged")
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
	}
}
