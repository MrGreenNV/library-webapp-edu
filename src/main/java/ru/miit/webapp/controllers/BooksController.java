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
import ru.miit.webapp.services.BookService;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;

    @ModelAttribute("addBookDTO")
    public AddBookDTO initForm() {
        return new AddBookDTO();
    }

    @GetMapping("/add")
    public String addBook() {
        return "book-add";
    }

    @PostMapping("/add")
    public String addBook(@Valid AddBookDTO bookModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addBookDTO", bookModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBookDTO", bindingResult);

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

    @GetMapping("/book-details/{book-id}")
    public String showBookDetails(@PathVariable("book-id") String id, Model model) {
        model.addAttribute("bookDetails", bookService.bookDetailsById(id));

        return "book-details";
    }

    @GetMapping("/book-delete/{book-id}")
    public String deleteBook(@PathVariable("book-id") String id) {
        bookService.removeBook(id);

        return "redirect:/book/all";
    }

    @GetMapping("/book-receive/{book-id}")
    public String receiveBook(@PathVariable("book-id") String id) {
        bookService.receiveBook(id);

        return "redirect:/";
    }

    @GetMapping("/book-return/{book-id}")
    public String returnBook(@PathVariable("book-id") String id) {
        bookService.returnBook(id);

        return "redirect:/";
    }


}
