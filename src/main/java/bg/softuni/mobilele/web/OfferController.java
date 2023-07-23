package bg.softuni.mobilele.web;

import bg.softuni.mobilele.exeption.ObjectNotFoundException;
import bg.softuni.mobilele.model.dto.CreateOrUpdateOfferDto;
import bg.softuni.mobilele.model.dto.OfferDTO;
import bg.softuni.mobilele.model.dto.SearchOfferDTO;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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
                                    size = 4) Pageable pageable) {
        if (!model.containsAttribute("offers")) {
            model.addAttribute("offers", offerService.allOffers(pageable));
        }
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new CreateOrUpdateOfferDto());
        }
        model.addAttribute("brands", brandService.getAllBrands());
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid CreateOrUpdateOfferDto addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);
            return "redirect:/offers/add";
        }
        offerService.addOffer(addOfferModel, userDetails);
        return "details";
    }


    @GetMapping("/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "offer-search";
        }
        if (!model.containsAttribute("searchOfferModel")) {
            model.addAttribute("searchOfferModel", searchOfferDTO);
        }

        if (!searchOfferDTO.isEmpty()) {
            model.addAttribute("offers", offerService.searchOffer(searchOfferDTO));
        }
        return "offer-search";
    }


    @GetMapping("/{id}")
    public String getDetails(@PathVariable Long id, Model model) {

        OfferDTO offerDTO = offerService.getOfferDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with Id " + id + "not found"));
        model.addAttribute("offerDetail", offerDTO);
        return "details";
    }


    @PreAuthorize("isOwner(#id)")
   // @PreAuthorize("@offerService.isOwner(#principal.name,#id)")
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {
        offerService.deleteOfferById(id);
        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        var offer = offerService.getOfferDetails(id).
                orElseThrow(() -> new ObjectNotFoundException("Offer with ID "+ id + "not found"));

        model.addAttribute("offer", offer);

        return "offer-details";
    }

}
