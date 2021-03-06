package com.itaki.radyodinlenir.web.controller.adminpanel;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itaki.radyodinlenir.service.impl.ContactRequestServiceImpl;
import com.itaki.radyodinlenir.web.dto.ContactAnswerDTO;
import com.itaki.radyodinlenir.web.dto.ContactRequestDTO;
import com.itaki.radyodinlenir.web.tools.MailSendService;

@Controller
public class ContactRequestController {

	@Autowired
	ContactRequestServiceImpl contactRequestService;

	@Autowired
	MessageSource msgsrc;

	@Autowired
	MailSendService mailService;

	@Value(value = "${mail.username}")
	private String sender;

	@Value(value = "${mail.subject}")
	private String subject;

	@RequestMapping(value = "/admin/contactlist/{pageIndex}", method = RequestMethod.GET)
	public String getContactRequestList(Locale locale, Model model, @PathVariable(value = "pageIndex") Integer pageIndex) {
		try {
			int contactRequestCount = contactRequestService.getContactRequestCount();
			List<ContactRequestDTO> contactlist = null;
			int maxPageIndex = (int) Math.ceil((double) contactRequestCount / 10);
			if (pageIndex == null || pageIndex < 1 || pageIndex > maxPageIndex) {
				pageIndex = 1;
			}
			contactlist = contactRequestService.getContactRequestForPager(pageIndex, 10);
			model.addAttribute("maxPageIndex", maxPageIndex);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("contactlist", contactlist);
			return "contactlist";
		} catch (Exception e) {
			return "error500";
		}
	}

	@RequestMapping(value = "/admin/contactlist/{deleteid}/delete", method = RequestMethod.GET)
	public String deleteContactRequestWithId(@PathVariable(value = "deleteid") Long id, Model model, final RedirectAttributes redirectAttributes, Locale locale) {
		try {
			contactRequestService.deleteContactRequest(id);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", msgsrc.getMessage("Form.Succesfull", new String[] {}, locale));
			return "redirect:/admin/contactlist/1";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", msgsrc.getMessage("Form.Alert", new String[] {}, locale));
			return "redirect:/admin/contactlist/1";
		}
	}

	@RequestMapping(value = "/admin/contactlist/{detailid}/details", method = RequestMethod.GET)
	public String detailsContactRequestWithId(@PathVariable(value = "detailid") Long id, Model model, Locale locale) {
		try {
			model.addAttribute("contactAnswer", new ContactAnswerDTO());
			model.addAttribute("contactdetails", contactRequestService.getContactRequestWithId(id));
			return "contactdetails";

		} catch (Exception e) {
			return "error500";
		}
	}

	@RequestMapping(value = "/admin/contactlist/{detailid}/details", method = RequestMethod.POST)
	public String answerContactRequestWithId(@PathVariable(value = "detailid") Long id, @ModelAttribute("contactAnswer") ContactAnswerDTO contactAnswer, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, Locale locale) {
		try {
			if (result.hasErrors()) {
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", msgsrc.getMessage("Form.Alert", new String[] {}, locale));
				return "contactdetails";
			}
			ContactRequestDTO contactdetails = contactRequestService.getContactRequestWithId(id);
			mailService.sendMail(sender, contactdetails.getEmail(), subject, contactAnswer.getBody());
			contactdetails.setWasAnswered(true);
			contactRequestService.updateContactRequest(contactdetails);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", msgsrc.getMessage("Form.Succesfull", new String[] {}, locale));
			return "redirect:/admin/contactlist/1";
		} catch (Exception e) {
			return "error500";
		}
	}

}