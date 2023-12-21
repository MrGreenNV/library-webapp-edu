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
import ru.miit.webapp.models.dto.AddAuthorDTO;
import ru.miit.webapp.services.AuthorService;

@Controller
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorsController {

    private final AuthorService authorService;

    @ModelAttribute("authorModel")
    public AddAuthorDTO initAuthor() {
        return new AddAuthorDTO();
    }

    @GetMapping("/add")
    public String addAuthor() {
        return "author-add";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid AddAuthorDTO authorModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("authorModel", authorModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorModel",
            bindingResult);
            return "redirect:/authors/add";
        }

        authorService.addAuthor(authorModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllAuthors(Model model) {

        model.addAttribute("authorInfos", authorService.allAuthors());

        return "author-all";
    }

    @GetMapping("/author-details/{author-name}")
    public String showAuthorDetails(@PathVariable("author-name") String authorName, Model model) {

        model.addAttribute("authorDetails", authorService.authorDetails(authorName));

        return "author-details";
    }

    @GetMapping("/author-delete/{author-name}")
    public String deleteAuthor(@PathVariable("author-name") String authorName) {

        authorService.removeAuthor(authorName);

        return "redirect:/authors/all";
    }
}