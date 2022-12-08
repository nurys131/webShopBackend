package pl.project.shop.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.common.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
