package com.luckey.model;

import com.luckey.adapter.AbsMoreBaseAdapter;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable, AbsMoreBaseAdapter.OnType {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long id;
	public String content;
	public double longitude;
	public double latitude;
	public String address;
	public int commentCount;
	public String author;
	public String authorPhoto;
	public long createDt;
	public List<Image> listImage;
	public long userId;
	public int likeCount;
	public int isLike;

	@Override
	public int getType() {
		if (listImage.size()>1){
			return 0;
		}else{
			return 1;
		}
	}
//	public List<Like> listLike;
}
