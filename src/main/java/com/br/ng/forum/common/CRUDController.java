package com.br.ng.forum.common;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.br.ng.forum.config.exceptions.BadRequestException;
import com.br.ng.forum.config.exceptions.ObjectNotFoundException;
import com.br.ng.forum.domains.DomainEntity;
import com.devskiller.friendly_id.FriendlyId;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.NonNull;

@Controller
public abstract class CRUDController<VM extends CRUDViewModel, E extends DomainEntity>{
    

    // Path to templates
    public static final String PATH_TO_EDIT = "/edit";
	public static final String PATH_TO_SEARCH = "/search";

    @NonNull
	private final String basePath;
	@NonNull
	private final String baseURL;
	@NonNull
	private final CRUDApplicationService<VM, E> CRUDApplicationService;

    public CRUDController(@NonNull CRUDControllerInitializer<VM, E> controllerInitializer){
        super();
        this.basePath = controllerInitializer.getBasePath();
		this.baseURL = controllerInitializer.getBaseURL();
		this.CRUDApplicationService = controllerInitializer.getCRUDApplicationService();
    }

    @RequestMapping
	public ModelAndView search(RedirectAttributes redirectAttributes, HttpSession session) {
		return getSearchModelAndView();
	}

    @RequestMapping(path = "/new")
	public ModelAndView newObject(@ModelAttribute("viewModel") VM vmObject,
			RedirectAttributes redirectAttributes, HttpSession session) {

		setNewObjectVariables(vmObject);
		return getNewModelAndView(vmObject);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteObject(@RequestParam(name = "friendlyHash") String friendlyHash,
			RedirectAttributes redirectAttributes, HttpSession session) {

		if(null == friendlyHash || friendlyHash.isEmpty()){
			throw new BadRequestException("Parâmetros não suportados");
		}

		this.CRUDApplicationService.removeLogicallyByHash(FriendlyId.toUuid(friendlyHash));

		return search(redirectAttributes, session);
	}

    @GetMapping(path = "/{friendlyHash}")
	public ModelAndView edit(@PathVariable(name = "friendlyHash", required = true) String friendlyHash,
			RedirectAttributes redirectAttributes, HttpSession session) {


		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(redirectAttributes.asMap());

	    Optional<VM> optional = this.CRUDApplicationService.getEnabledForEditing(
            FriendlyId.toUuid(friendlyHash)
        );
		if (optional.isPresent()) {
			VM vmObject = optional.get();
			modelAndView = getEditModelAndView(vmObject);
			setUpdateObjectVariables(vmObject);
			modelAndView.addObject("viewModel", vmObject);
		} else {
		    throw new ObjectNotFoundException("Não encontrado");
		}

		return modelAndView;
	}


    @PostMapping(path = "/save")
	public ModelAndView save(@Valid @ModelAttribute("viewModel") VM viewModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {

		ModelAndView modelAndView = null;

		if (bindingResult.hasErrors()) {
			modelAndView = getEditModelAndView(viewModel);
			return modelAndView;
		}

	    CRUDApplicationService.save(viewModel);

		return this.getSearchModelAndView();
	}

	// ***********************************************************************


    public void setNewObjectVariables(VM vmObject) {
		vmObject.setFriendlyHash(vmObject.getFriendlyHash());
	}

	public void setUpdateObjectVariables(VM vmObject) {
		vmObject.setFriendlyHash(vmObject.getFriendlyHash());
	}

    protected ModelAndView getSearchModelAndView() {
		return new ModelAndView(basePath + PATH_TO_SEARCH);
	}

    protected ModelAndView getNewModelAndView(VM vmObject) {
		return new ModelAndView(basePath + PATH_TO_EDIT);
	}

    protected ModelAndView getEditModelAndView(VM vmObject) {
		return new ModelAndView(basePath + PATH_TO_EDIT);
	}

}
