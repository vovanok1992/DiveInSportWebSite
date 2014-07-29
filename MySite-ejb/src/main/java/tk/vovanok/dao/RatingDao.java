package tk.vovanok.dao;

import tk.vovanok.entities.RatingVote;

public interface RatingDao extends GenericDao<RatingVote, Long>{

    public boolean exists(Long id, Long id0);
    
}
