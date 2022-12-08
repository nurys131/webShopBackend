package pl.project.shop.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.common.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProductIdAndModerated(Long productId, boolean moderated);
}
