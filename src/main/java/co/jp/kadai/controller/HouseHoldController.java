package co.jp.kadai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.jp.kadai.dto.HouseHoldDto;
import co.jp.kadai.dto.HouseHoldListDto;
import co.jp.kadai.entity.HouseHold;
import co.jp.kadai.service.HouseHoldService;
import jakarta.validation.Valid;

@Controller
public class HouseHoldController {

	@Autowired
	HouseHoldService houseHoldService;

	@GetMapping(value = "/")
	public String viewList(Model model) {
		List<HouseHoldListDto> houseHoldList = houseHoldService.getAllByYearMonth();
		model.addAttribute("houseHoldList", houseHoldList);
		return "householdlist";
	}

	@GetMapping(value = "/details")
	public String viewDetails(Model model, @RequestParam("year") int year, @RequestParam("month") int month) {
		List<HouseHold> houseHoldDetailList = houseHoldService.getDetails(year, month);
		model.addAttribute("houseHoldDetailList", houseHoldDetailList);
		model.addAttribute("houseHoldDetailTotal", houseHoldService.getDetailTotal(year, month));
		return "householddetaillist";
	}

	@GetMapping(value = "/details/register")
	public String viewRegister(Model model) {
		model.addAttribute("form", new HouseHoldDto());
		return "register";
	}

	@PostMapping(value = "/details/register")
	public String submitRegister(Model model, @Valid @ModelAttribute("form") HouseHoldDto houseHoldDto, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		houseHoldService.register(houseHoldDto);
		return "redirect:/";
	}

	@GetMapping(value = "/details/update/{id}")
	public String viewUpdate(Model model, @PathVariable int id) {
		HouseHold houseHold = houseHoldService.getHouseHold(id);
		model.addAttribute("form", houseHold);
		model.addAttribute("id", id);
		return "update";
	}
	
	@PostMapping(value = "/details/update/{id}")
	public String submitUpdate(Model model, @PathVariable int id, @Valid @ModelAttribute("form") HouseHoldDto houseHoldDto, BindingResult result) {
		System.out.println("hellooooooooooooooo");
		if (result.hasErrors()) {
			return "update";
		}
		houseHoldService.register(houseHoldDto);
		return "redirect:/";
	}

}
