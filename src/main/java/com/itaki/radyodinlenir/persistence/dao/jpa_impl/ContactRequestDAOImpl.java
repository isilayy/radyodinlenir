package com.itaki.radyodinlenir.persistence.dao.jpa_impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.itaki.radyodinlenir.persistence.dao.ContactRequestDAO;
import com.itaki.radyodinlenir.persistence.model.ContactRequest;
import com.itaki.radyodinlenir.util.QueryUtil;

@Repository
public class ContactRequestDAOImpl extends GenericDAOImpl<ContactRequest> implements ContactRequestDAO {

	
	public ContactRequestDAOImpl() {
		setClazz(ContactRequest.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContactRequest> getContactRequestForPager(int page, int itemSize) {
		Query query = QueryUtil.getPageFromQuery(em.createNamedQuery(ContactRequest.ALL_CONTACT_REQUESTS), page, itemSize);
		return (List<ContactRequest>) query.getResultList();
	}

}
