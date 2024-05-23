package zuhriddinscode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import zuhriddinscode.service.BookService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity< ? > home(){
        return ResponseEntity.ok(bookService.getPopularBooks());
    }


//    @GetMapping
//    public String home(Model model) {
//        model.addAttribute("perfumes",
//                perfumeService.getPopularPerfumes());  // personally recomended
//        return Pages.HOME;
//    }
}