package telran.java51.simplewebservice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;



@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonFoodDto {
	
	String fullName;
	@Singular
	List<String> foods;

}
