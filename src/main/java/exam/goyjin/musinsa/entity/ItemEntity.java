package exam.goyjin.musinsa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "item", indexes = {
		@Index(name = "idx_item_category", columnList = "category"),
		@Index(name = "idx_item_price", columnList = "price"),
		@Index(name = "idx_item_brand", columnList = "brand"),
})
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String brand;
	private String category;
	private double price;
	private long stock;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;


}
