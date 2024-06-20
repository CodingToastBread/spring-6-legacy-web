package coding.toast.springweblegacy.layout.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
@RequiredArgsConstructor
public class LayoutTestController {

    @GetMapping("/sample")
    public String layout1(Model model) {
        model.addAttribute("passValue", "test text");
        return "layouts/sample-layout-content";
    }
}
