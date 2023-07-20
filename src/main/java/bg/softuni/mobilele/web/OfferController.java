package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.model.dto.SearchOfferDTO;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("offers")
public class OfferController {

//    @ModelAttribute("addOfferModel")
//    public AddOfferDto initOfferModel() {
//        return new AddOfferDto();
//    }

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String allOffers(Model model,
                            @PageableDefault(
                                    sort = "created",
                                    direction = Sort.Direction.DESC,
                                    page = 0,
                                    size = 2) Pageable pageable) {
        if (!model.containsAttribute("offers")) {
            model.addAttribute("offers", offerService.allOffers(pageable));
        }
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new AddOfferDto());
        }
        model.addAttribute("brands", brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);
            return "redirect:/offers/add";
        }
        offerService.addOffer(addOfferModel, userDetails);
        return "redirect:/offers/all";
    }

    @GetMapping("/search")
    public String searchOffer() {
        return "offer-search";
    }

    @PostMapping("/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchOfferModel", searchOfferDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "redirect:/offers/search";
        }

        return String.format("redirect:/offers/search/%s", searchOfferDTO.getQuery());
    }

    @GetMapping("/search/{query}")
    public String searchResults(@PathVariable String query, Model model) {
        model.addAttribute("offers", this.offerService.findOfferByOfferName(query));
        return "offer-search";
    }



    @ModelAttribute(name = "searchOfferModel")
    private SearchOfferDTO initSearchModel() {
        return new SearchOfferDTO();
    }

    @GetMapping("/{id}/details")
    private String getDetails(@PathVariable Long id) {
        return "details";
    }
}
