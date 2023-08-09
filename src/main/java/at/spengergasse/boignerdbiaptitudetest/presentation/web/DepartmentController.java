package at.spengergasse.boignerdbiaptitudetest.presentation.web;

import at.spengergasse.boignerdbiaptitudetest.domain.Department;
import at.spengergasse.boignerdbiaptitudetest.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor

@Controller
@RequestMapping(DepartmentController.BASE_URL)
public class DepartmentController {

    private final DepartmentService departmentService;

    public static final String BASE_URL = "";
    public static final String ROUTE_INDEX = "/";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String SHOW_DEPARTMENT = "/department/show" + PATH_VAR_ID;
    public static final String NEW_DEPARTMENT = "/department/create";
    public static final String UPDATE_DEPARTMENT = "/department/update" + PATH_VAR_ID;
    public static final String DELETE_DEPARTMENT = "/department/delete" + PATH_VAR_ID;

    @GetMapping({"", ROUTE_INDEX})
    public String index(Model model){
        model.addAttribute("departments", departmentService.getDepartments());
        return "index";
    }

    @GetMapping(SHOW_DEPARTMENT)
    public String showDepartment(Model model, @PathVariable ObjectId id){
        Department selectedDepartment = departmentService.getDepartment(id).orElseThrow(() -> new IllegalArgumentException(String.format("Can't find department %s", id)));
        model.addAttribute("selectedDepartment", selectedDepartment);
        return "department/showDepartment";
    }

    @GetMapping(NEW_DEPARTMENT)
    public String showCreateDepartment(Model model){
        model.addAttribute("departmentForm", new CreateDepartmentForm());
        return "department/createDepartment";
    }

    @PostMapping(NEW_DEPARTMENT)
    public String createDepartment(@Valid @ModelAttribute CreateDepartmentForm form, BindingResult brCreateDepartmentForm, Model model){
        if (brCreateDepartmentForm.hasErrors()){
            model.addAttribute("departmentForm", form);
            return "department/createDepartment";
        }
        departmentService.createDepartment(form);
        return "redirect:"+ROUTE_INDEX;
    }

    @GetMapping(UPDATE_DEPARTMENT)
    public String showUpdateDepartment(@PathVariable ObjectId id, Model model){
        model.addAttribute("departmentForm", new CreateDepartmentForm());
        return "department/updateDepartment";
    }

    @PostMapping(UPDATE_DEPARTMENT)
    public String updateDepartment(@PathVariable ObjectId id, @Valid @ModelAttribute CreateDepartmentForm form, BindingResult brUpdateDepartmentForm, Model model){
        if (brUpdateDepartmentForm.hasErrors()){
            model.addAttribute("departmentForm", form);
            return "department/updateDepartment";
        }
        departmentService.updateDepartment(id, form);
        return "redirect:"+ROUTE_INDEX;
    }

    @GetMapping(DELETE_DEPARTMENT)
    public String deleteDepartment(@PathVariable ObjectId id){
        departmentService.deleteDepartment(id);
        return "redirect:"+ROUTE_INDEX;
    }
}
