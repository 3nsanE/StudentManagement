package com.newlearn.stud_man_sys;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class Student_DAO {
    private final JdbcTemplate jdbcTemplate;

    public Student_DAO(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    private final RowMapper<Student> StudentRowMapper = (rs, row) ->
            new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("course"),
                    rs.getDouble("marks")
            );

    //Create
    public Student save(Student student){
        String sql="insert into students(name,age,course,marks) values(?,?,?,?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection ->{
                    var ps=connection.prepareStatement(sql,
                            java.sql.Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, student.getName());
                    ps.setInt(2, student.getAge());
                    ps.setString(3, student.getCourse());
                    ps.setDouble(4, student.getMarks());

                    return ps;
                },keyHolder);

                student.setId(keyHolder.getKey().intValue());
        return student;
    }

    //Read all
    public List<Student> findAll(){
        String sql="select * from students order by id";

        return  jdbcTemplate.query(sql, StudentRowMapper);
    }

    //Read one
    public Optional<Student> findById(int id){
        String sql="select * from students where id=?";

        try{
            Student student=jdbcTemplate.queryForObject(sql,StudentRowMapper,id);
            return Optional.ofNullable(student);
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    //Update
    public boolean update(Student student){
        String sql="update students set name=?,age=?,course=?,marks=? where id=?";

        int rows=jdbcTemplate.update(
                sql,
                student.getName(),
                student.getAge(),
                student.getCourse(),
                student.getMarks(),
                student.getId());
        return rows>0;
    }

    //Update Marks
    public boolean updateMarks(int id, double marks){
        String sql="update students set marks=? where id=?";

        int row=jdbcTemplate.update(sql,marks,id);

        return row>0;
    }

    //Delete
    public boolean deleteById(int id){
        String sql="delete from students where id=?";

        int rows=jdbcTemplate.update(sql,id);
        return rows>0;
    }
}