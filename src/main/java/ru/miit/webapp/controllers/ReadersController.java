package ru.miit.webapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.miit.webapp.models.dto.AddReaderDTO;
import ru.miit.webapp.services.ReaderService;

@Controller
@RequestMapping("readers")
@RequiredArgsConstructor
public class ReadersController {

    private final ReaderService readerService;

    @GetMapping("/add")
    public String addReader() {
        return "reader-add";
    }

    @ModelAttribute("readerModel")
    public AddReaderDTO initReader() {
        return new AddReaderDTO();
    }

    @PostMapping("/add")
    public String addReader(@Valid AddReaderDTO readerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("readerModel", readerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.readerModel",
                    bindingResult);
            return "redirect:/readers/add";
        }
        readerService.addReader(readerModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllReaders(Model model) {
        model.addAttribute("allReaders", readerService.allReader());

        return "reader-all";
    }

    @GetMapping("/reader-details/{reader-name}")
    public String companyDetails(@PathVariable("reader-name") String readerName, Model model) {
        model.addAttribute("readerDetails", readerService.readerDetails(readerName));

        return "reader-details";
    }

    @GetMapping("/reader-delete/{reader-name}")
    public String deleteCompany(@PathVariable("reader-name") String readerName) {
        readerService.deleteReader(readerName);

        return "redirect:/readers/all";
    }
}
