package shopManager.dto;

import java.util.List;

import lombok.Data;

@Data
public class ItemDto {
	private int idx;
	private String image;
	private String name;
	private int price;
	private int stock;
	private String manager;
	private List<ImageDto> listImages;
	private List<ImageDto> contentImages;
}
