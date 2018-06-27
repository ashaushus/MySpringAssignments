package com.project.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.demo.models.Slideshow;
import com.project.demo.models.SlideshowItem;
import com.project.demo.repository.SlideshowRepository;

@Service
public class SlideshowSvcImpl implements SlideshowService {

	
	private SlideshowRepository slideshowRepository;
	@Autowired
	public void setSlideshowRepository(SlideshowRepository slideshowRepository) {
		this.slideshowRepository = slideshowRepository;
	}
	
	public List<Slideshow> findAll() {

		return slideshowRepository.findAll();
	}

	@Override
	public Slideshow getForEditItems(Integer slideshowId) {

		return slideshowRepository.findSlideshowWithItemsById(slideshowId);
				
	}

	@Override
	public void updateSlideshowItems(Integer slideshowId, Map<String, String[]> parameters, MultipartFile[] newItemsFiles) throws Exception {

		Slideshow slideshow = slideshowRepository.findSlideshowWithItemsById(slideshowId);
		
		if(slideshow == null) {
			throw new Exception("slideshow Not Found");
		}
		
		deleteNotFoundItems(slideshow, parameters);
		updateModifiedItemsFromParameters(slideshow, parameters);

		slideshowRepository.save(slideshow);
	}

	@Override
	public void deleteNotFoundItems(Slideshow slideshow, Map<String, String[]> parameters) {

		Set<SlideshowItem> deletedItems = findDeletedItems(slideshow, parameters);
		deleteItems(slideshow, deletedItems);
	}

	@Override
	public Set<SlideshowItem> findDeletedItems(Slideshow slideshow, Map<String, String[]> parameters) {

		Set<SlideshowItem> deletedItems = new HashSet<SlideshowItem>();
		
		if(parameters.get("itemIds") == null ) {
			for(SlideshowItem item : slideshow.getSlideshowItems()) {
				deletedItems.add(item);
			}
			return deletedItems;
		}
			
		for(SlideshowItem item : slideshow.getSlideshowItems()) {
			boolean exist=false;
			for(String itemId: parameters.get("itemIds")) {
				if(item.getId() == Integer.parseInt(itemId)) {
					exist=true;
				}
			}
		//	exist=true;
			if(exist == false) {
				deletedItems.add(item);
			}
		}
		
		return deletedItems;
	}

	@Override
	public void deleteItems(Slideshow slideshow, Set<SlideshowItem> deletedItems) {

		if(deletedItems.isEmpty()) {
			return;
		}
		Set<Integer> deleteIds = new HashSet<Integer>();
		
		for(SlideshowItem item : deletedItems) {
			
			slideshow.removeSlideshowItem(item);
			deleteIds.add(item.getId());
			
			
		}
		
		slideshowRepository.deleteItems(deleteIds);
		
	}

	@Override
	public void updateModifiedItemsFromParameters(Slideshow slideshow, Map<String, String[]> parameters) {
		
		if(!parameters.containsKey("itemIds") || parameters.get("itemTitles") == null ||
				parameters.get("itemOrderings") == null) {
			return;
		}
		
		int size = parameters.get("itemIds").length;
		 
		for(int i=0; i<size; i++) {
			String title = parameters.get("itemTitles")[i];
			int id = Integer.parseInt(parameters.get("itemIds")[i]);
			int ordering = Integer.parseInt(parameters.get("itemOrderings")[i]);
			
			System.out.println("id is null? "+id);
			
			// find related slideshowItem and update it
			for(SlideshowItem item : slideshow.getSlideshowItems()) {
				
				System.out.println("my id id: "+item.getId());
				if(item.getId() == null) {
					continue;
				}
				else if(item.getId() == id) {
					item.setTitle(title);
					item.setOrdering(ordering);
					
					break;
				}
			}
		}
	}

	@Override
	public Slideshow getSlideshowForShowById(int id) {

		return slideshowRepository.getSlideshowForShowById(id);
	}
}
