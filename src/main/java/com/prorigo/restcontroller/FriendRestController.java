package com.prorigo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prorigo.model.FriendsInfo;

import com.prorigo.service.FriendService;

@RestController
public class FriendRestController {

	@Autowired
	private FriendService friendService;
	
	
	@PostMapping("/savefriend")
	public ResponseEntity<String> addFriend(@RequestBody FriendsInfo friendInfo) {
		 friendService.saveFriend(friendInfo);
		 return new ResponseEntity<>("Freind Saved",HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<FriendsInfo>> getAllFriends(){
		List<FriendsInfo> info=friendService.getAllFriends();
		return new ResponseEntity<>(info,HttpStatus.OK);
	}
	
//	@PutMapping("/updatefriend")
//	public ResponseEntity<String> updateFriendInfo(@RequestBody FriendsInfo info){
//		String status=friendService.saveFriend(info);
//		return new ResponseEntity<>(status,HttpStatus.OK);
//	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<FriendsInfo> updateBirthday(@PathVariable Long id, @RequestBody FriendsInfo updatedBirthday) {
		FriendsInfo existingBirthday = friendService.getBirthdayById(id);
 
		if (existingBirthday == null) {
			// Handle the case where the birthday with the given ID doesn't exist.
			return ResponseEntity.notFound().build();
		}
 
		// Update the existing birthday with the new data
		existingBirthday.setName(updatedBirthday.getName());
		existingBirthday.setDateOfBirth(updatedBirthday.getDateOfBirth());
		existingBirthday.setMail(updatedBirthday.getMail());
 
		// Save the updated birthday
		friendService.updateBirthday(existingBirthday);
 
		return ResponseEntity.ok(existingBirthday);
	
}
	
	@DeleteMapping("/all/{id}")
	public ResponseEntity<String> deleteFriendInfo(@PathVariable Long id){
		String status =friendService.deleteFriendById(id);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}
