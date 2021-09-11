package com.javahyang.springbootcrudcodedeploy.ui;

import com.javahyang.springbootcrudcodedeploy.application.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final ClientService clientService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "index";
    }

    @GetMapping("/client")
    public String clientSave() {
        return "client";
    }

    @GetMapping("/client/{id}")
    public String clientUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClinet(id));
        return "client";
    }
}
