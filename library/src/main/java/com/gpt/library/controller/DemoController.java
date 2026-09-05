package com.gpt.library.controller;

import com.gpt.library.dto.request.AuthorRequestDTO;
import com.gpt.library.dto.request.BookRequestDTO;
import com.gpt.library.dto.request.CategoryRequestDTO;
import com.gpt.library.service.AuthorService;
import com.gpt.library.service.BookService;
import com.gpt.library.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public DemoController(BookService bookService,
                          AuthorService authorService,
                          CategoryService categoryService)
    {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }


    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "books";
    }


    @GetMapping("/authors")
    public String authors(Model model) {
        model.addAttribute("authors", authorService.getAuthors());
        return "authors";
    }


    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "categories";
    }

    @GetMapping("/authors/new")
public String newAuthor(Model model) {

    model.addAttribute("author", new AuthorRequestDTO(""));
    model.addAttribute("formAction", "/authors/save");
    model.addAttribute("isUpdate", false);

    return "add-author";
}


@PostMapping("/authors/save")
public String saveAuthor(
        @Valid @ModelAttribute("author") AuthorRequestDTO authorRequestDTO,
        BindingResult bindingResult,
        Model model) {

    if (bindingResult.hasErrors()) {

        model.addAttribute("formAction", "/authors/save");
        model.addAttribute("isUpdate", false);

        return "add-author";
    }

    authorService.addAuthor(authorRequestDTO);

    return "redirect:/authors";
}


@GetMapping("/categories/new")
public String newCategory(Model model) {

    model.addAttribute("category", new CategoryRequestDTO(""));
    model.addAttribute("formAction", "/categories/save");
    model.addAttribute("isUpdate", false);

    return "add-category";
}


@PostMapping("/categories/save")
public String saveCategory(
        @Valid @ModelAttribute("category") CategoryRequestDTO categoryRequestDTO,
        BindingResult bindingResult,
        Model model) {

    if (bindingResult.hasErrors()) {

        model.addAttribute("formAction", "/categories/save");
        model.addAttribute("isUpdate", false);

        return "add-category";
    }

    categoryService.addCategory(categoryRequestDTO);

    return "redirect:/categories";
}


    @GetMapping("/books/new")
    public String addBookForm(Model model) {

        model.addAttribute("book",
                new BookRequestDTO("", "", null, 0, 0));

        model.addAttribute("authors", authorService.getAuthors());
        model.addAttribute("categories", categoryService.getCategories());

        model.addAttribute("formAction", "/books/save");
        model.addAttribute("isUpdate", false);

        return "add-book";
    }


    @PostMapping("/books/save")
    public String saveBook(
            @Valid @ModelAttribute("book") BookRequestDTO dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("categories", categoryService.getCategories());
            model.addAttribute("formAction", "/books/save");
            model.addAttribute("isUpdate", false);

            return "add-book";
        }

        bookService.addBook(dto);

        return "redirect:/books";
    }

    @GetMapping("/books/update/{id}")
    public String updateBookForm(@PathVariable int id, Model model) {

        model.addAttribute("book", bookService.getBookForUpdate(id));
        model.addAttribute("bookId", id);

        model.addAttribute("authors", authorService.getAuthors());
        model.addAttribute("categories", categoryService.getCategories());

        model.addAttribute("formAction", "/books/update/" + id);
        model.addAttribute("isUpdate", true);

        return "add-book";
    }


    @PostMapping("/books/update/{id}")
    public String updateBook(
            @PathVariable int id,
            @Valid @ModelAttribute("book") BookRequestDTO dto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.getAuthors());
            model.addAttribute("categories", categoryService.getCategories());
            model.addAttribute("formAction", "/books/update/" + id);
            model.addAttribute("bookId", id);
            model.addAttribute("isUpdate", true);

            return "add-book";
        }

        bookService.updateBook(id, dto);

        return "redirect:/books";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/authors/update/{id}")
    public String updateAuthorForm(@PathVariable int id, Model model) {

        model.addAttribute("author", authorService.getAuthorForUpdate(id));
        model.addAttribute("authorId", id);
        model.addAttribute("formAction", "/authors/update/" + id);
        model.addAttribute("isUpdate", true);

        return "add-author";
    }

    @PostMapping("/authors/update/{id}")
    public String updateAuthor(
            @PathVariable int id,
            @Valid @ModelAttribute("author") AuthorRequestDTO dto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authorId", id);
            model.addAttribute("formAction", "/authors/update/" + id);
            model.addAttribute("isUpdate", true);

            return "add-author";
        }

        authorService.updateAuthor(id, dto);

        return "redirect:/authors";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategoryForm(@PathVariable int id, Model model) {

        model.addAttribute("category", categoryService.getCategoryForUpdate(id));
        model.addAttribute("categoryId", id);
        model.addAttribute("formAction", "/categories/update/" + id);
        model.addAttribute("isUpdate", true);

        return "add-category";
    }

    @PostMapping("/categories/update/{id}")
    public String updateCategory(
            @PathVariable int id,
            @Valid @ModelAttribute("category") CategoryRequestDTO dto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryId", id);
            model.addAttribute("formAction", "/categories/update/" + id);
            model.addAttribute("isUpdate", true);

            return "add-category";
        }

        categoryService.updateCategory(id, dto);

        return "redirect:/categories";
    }

    @PostMapping("/authors/delete/{id}")
    public String deleteAuthorForm(@PathVariable int id, Model model) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
    @PostMapping("/categories/delete/{id}")
    public String deleteCategoryForm(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}


