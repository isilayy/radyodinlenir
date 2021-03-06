package com.itaki.radyodinlenir.persistence.dao.jpa_impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.itaki.radyodinlenir.persistence.dao.RadioStationDAO;
import com.itaki.radyodinlenir.persistence.model.RadioStation;
import com.itaki.radyodinlenir.util.QueryUtil;

@Repository
public class RadioStationDAOImpl extends GenericDAOImpl<RadioStation> implements RadioStationDAO {

	public RadioStationDAOImpl() {
		setClazz(RadioStation.class);
	}

	@Override
	public RadioStation getRadioStationByName(String name) throws NoResultException {
		Query query = em.createNamedQuery(RadioStation.RADIO_STATION_FIND_BY_NAME).setParameter("name", name);
		return (RadioStation) query.getSingleResult();
	}

	@Override
	public RadioStation getRadioStationWithCleanUrl(String cleanUrl) throws NoResultException {
		Query query = em.createNamedQuery(RadioStation.RADIO_STATION_BY_CLEAN_URL).setParameter("cleanUrl", cleanUrl);
		return (RadioStation) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioStation> getRadioStationForPager(int page, int itemSize) {
		Query query = QueryUtil.getPageFromQuery(em.createNamedQuery(RadioStation.GET_ALL_RADIO_STATIONS), page, itemSize);
		return (List<RadioStation>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RadioStation> getRadioStationForUserPager(int page, int itemSize) {
		Query query = QueryUtil.getPageFromQuery(em.createNamedQuery(RadioStation.GET_ALL_RADIO_STATIONSFORUSER), page, itemSize);
		return (List<RadioStation>) query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<RadioStation> getRadioStationForPagerWithMusicType(int page, int itemSize, int musicTypeId) {
		Query query = QueryUtil.getPageFromQuery(em.createNamedQuery(RadioStation.GET_RADIO_STATIONS_BY_MUSIC_TYPE).setParameter("musicType", musicTypeId), page, itemSize);
		return (List<RadioStation>) query.getResultList();
	}

	@Override
	public int getRadioStationsCountWithMusicTypeId(int musicTypeId) {
		Query query = em.createNamedQuery(RadioStation.GET_RADIO_STATIONS_BY_MUSIC_TYPE_COUNT).setParameter("musicType", musicTypeId);
		return ((Long) query.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioStation> searchRadioStationWithSearchText(String searchText) {
		searchText = "%".concat(searchText).concat("%");
		Query query = em.createNamedQuery(RadioStation.SEARCH_RADIO_STATION_BY_NAME_AND_DESCRIPTION).setParameter("name", searchText).setParameter("description", searchText);
		return (List<RadioStation>) query.getResultList();
	}

	@Override
	public RadioStation getRadioStationWithID(Integer id) throws NoResultException {
		Query query = em.createNamedQuery(RadioStation.RADIO_STATION_BY_ID).setParameter("id", id);
		return (RadioStation) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioStation> getNewestRadioStationForPage(int page, int itemSize) {
		Query query = QueryUtil.getPageFromQuery(em.createNamedQuery(RadioStation.GET_NEWEST_RADIO_STATIONS), page, itemSize);
		return (List<RadioStation>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioStation> getRadioStationForPagerWithCity(int page, int itemSize, int cityId) {
		Query query = QueryUtil.getPageFromQuery(em.createNamedQuery(RadioStation.GET_RADIO_STATIONS_BY_CITY).setParameter("city", cityId), page, itemSize);
		return (List<RadioStation>) query.getResultList();

	}

	@Override
	public int getRadioStationsCountWithCityId(int cityId) {
		Query query = em.createNamedQuery(RadioStation.GET_RADIO_STATIONS_BY_CITY_COUNT).setParameter("city", cityId);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public int getRadioStationForUserPagerCount() {
		Query query = em.createNamedQuery(RadioStation.GET_ALL_RADIO_STATIONSFORUSER_COUNT);
		return ((Long) query.getSingleResult()).intValue();
	}
}
