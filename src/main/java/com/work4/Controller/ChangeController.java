package com.work4.Controller;

import com.work4.Item.Person;
import com.work4.Item.Table;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeController {
    //点击"修改"按钮
    @GetMapping("/change")
    public String showChange(@ModelAttribute(value="row")Integer row, HttpServletRequest request, Model model){
        Object flag = request.getSession().getAttribute("login");
        if (null != flag){
            HttpSession session = request.getSession();
            Table table = (Table)session.getAttribute("table");
            Person person = table.getTable().elementAt(row);
            model.addAttribute("person", person);
            return "change";
        } else {
            return "redirect:/login";
        }
    }

    //在change.html点击提交
    @PostMapping("/checkchange")
    public String checkChange(Person person, Model model, HttpServletRequest request){
        if (null == request.getSession().getAttribute("login")){
            return "redirect:/login";
        } else{
            HttpSession session = request.getSession();
            Table table = (Table)session.getAttribute("table");
            table.addPerson(person);
            session.setAttribute("table", table);
            model.addAttribute("person", person);
            return "redirect:/list";
        }
    }
}