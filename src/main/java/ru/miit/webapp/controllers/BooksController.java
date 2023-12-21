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
import ru.miit.webapp.models.dto.AddBookDTO;
import ru.miit.webapp.services.AuthorService;
import ru.miit.webapp.services.BookService;

@Controller
@RequestMapping("books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("availableAuthor", authorService.allAuthors());
        return "book-add";
    }

    @ModelAttribute("bookModel")
    public AddBookDTO initBook() {
        return new AddBookDTO();
    }

    @PostMapping("/add")
    public String addBook(@Valid AddBookDTO bookModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookModel", bookModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookModel",
                    bindingResult);
            return "redirect:/books/add";
        }
        bookService.addBook(bookModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllBooks(Model model) {
        model.addAttribute("allBooks", bookService.allBooks());
        return "book-all";
    }

    @GetMapping("/book-details/{book-title}")
    public String showBookDetails(@PathVariable("book-title") String title, Model model) {
        model.addAttribute("bookDetails", bookService.bookDetails(title));

        return "book-details";
    }

    @GetMapping("/book-delete/{book-title}")
    public String deleteBook(@PathVariable("book-title") String title) {
        bookService.removeBook(title);

        return "redirect:/books/all";
    }
}
