package br.com.cafeteria.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import static androidx.room.OnConflictStrategy.REPLACE;
import br.com.cafeteria.entity.Cafe;

@Dao
public interface CafeDAO {
    @Query("SELECT * FROM Cafe")
    List<Cafe> getAllCafe();

    @Insert(onConflict = REPLACE)
    void insert(Cafe cafe);

    @Update
    public void update(Cafe cafe);

    @Delete
    public void delete(Cafe cafe);
}
