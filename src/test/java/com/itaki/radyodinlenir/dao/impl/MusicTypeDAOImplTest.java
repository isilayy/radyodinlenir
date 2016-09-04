package com.itaki.radyodinlenir.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itaki.radyodinlenir.persistence.dao.MusicTypeDAO;
import com.itaki.radyodinlenir.persistence.model.MusicType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:com/itaki/radyodinlenir/config/test-persistence-context.xml", "classpath:com/itaki/radyodinlenir/config/dao-context.xml" })
@Transactional
public class MusicTypeDAOImplTest {
	
	@Autowired
	private MusicTypeDAO musicTypeDAO;
	
	private static MusicType musicType1;
	private static MusicType musicType2;
	private static MusicType musicType3;
	private static MusicType musicType4;

	@BeforeClass
	public static void init() {
		musicType1 = createMusicType(1, "name1", (short)1);
		musicType2 = createMusicType(2, "name2", (short)2);
		musicType3 = createMusicType(3, "name3", (short)3);
		musicType4 = createMusicType(4, "name4", (short)4);
	}
	
	@Test
	public void music_types_table_initial_must_have_four_items_and_the_first_item_is_must_be_the_highest_priority() {
		List<MusicType> musicTypeList = musicTypeDAO.getAllMusicTypes();
		assertEquals(4, musicTypeList.size());
		assertEquals(musicType4, musicTypeList.get(0));
		assertEquals(musicType1, musicTypeList.get(3));
	}
	
	public static MusicType createMusicType(int id, String name, short priority) {
		MusicType musicType = new MusicType();
		musicType.setId(id);
		musicType.setName(name);
		musicType.setPriority(priority);
		return musicType;
	}
}
