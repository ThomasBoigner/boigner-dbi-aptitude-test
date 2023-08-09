package at.spengergasse.boignerdbiaptitudetest.presentation.web;

import at.spengergasse.boignerdbiaptitudetest.domain.Department;
import at.spengergasse.boignerdbiaptitudetest.domain.Entry;
import at.spengergasse.boignerdbiaptitudetest.service.DepartmentService;
import at.spengergasse.boignerdbiaptitudetest.service.EntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static at.spengergasse.boignerdbiaptitudetest.presentation.web.DepartmentController.SHOW_DEPARTMENT;
import static at.spengergasse.boignerdbiaptitudetest.presentation.web.EntryController.BASE_URL;

@RequiredArgsConstructor

@Controller
@RequestMapping(BASE_URL)
public class EntryController {
    private final EntryService entryService;
    private final DepartmentService departmentService;

    public static final String BASE_URL = "/entry";
    public static final String ROUTE_INDEX = "/";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String CREATE_ENTRY = "/create" + PATH_VAR_ID;
    public static final String UPDATE_ENTRY = "/update/{departmentId}/{entryId}";
    public static final String DELETE_ENTRY = "/delete/{departmentId}/{entryId}";

    @GetMapping(CREATE_ENTRY)
    public String showCreateForm(@PathVariable ObjectId id, Model model){
        model.addAttribute("entryForm", new CreateEntryForm());
        model.addAttribute("departmentId", id);
        return "entry/createEntry";
    }

    @PostMapping(CREATE_ENTRY)
    public String createEntry(@PathVariable ObjectId id, @Valid @ModelAttribute CreateEntryForm form, BindingResult brCreateEntryForm, Model model){
        if (brCreateEntryForm.hasErrors()){
            model.addAttribute("entryForm", form);
            model.addAttribute("departmentId", id);
            return "entry/createEntry";
        }
        entryService.createEntry(form, id);
        return "redirect:"+SHOW_DEPARTMENT;
    }

    @GetMapping(UPDATE_ENTRY)
    public String showUpdateForm(@PathVariable ObjectId departmentId, @PathVariable ObjectId entryId, Model model){
        model.addAttribute("entryForm", new CreateEntryForm());
        model.addAttribute("departmentId", departmentId);
        return "entry/updateEntry";
    }

    @PostMapping(UPDATE_ENTRY)
    public String updateEntry(@PathVariable ObjectId departmentId, @PathVariable ObjectId entryId, @Valid @ModelAttribute CreateEntryForm form, BindingResult brUpdateEntryForm, Model model){
       if (brUpdateEntryForm.hasErrors()){
           model.addAttribute("entryForm", new CreateEntryForm());
           model.addAttribute("departmentId", departmentId);
           return "entry/updateEntry";
       }
       entryService.updateEntry(departmentId, entryId, form);
       return "redirect:/department/show/" + departmentId;
    }

    @GetMapping(DELETE_ENTRY)
    public String deleteEntry(@PathVariable ObjectId departmentId, @PathVariable ObjectId entryId){
        entryService.removeEntry(departmentId, entryId);
        return "redirect:/department/show/" + departmentId;
    }
}
