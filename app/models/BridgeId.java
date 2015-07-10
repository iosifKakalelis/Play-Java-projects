package models;
import java.util.*;
import javax.persistence.*;

import java.io.Serializable;
import java.lang.Object.*;
//import org.openqa.selenium.lift.find.Finder;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
@Embeddable
public class BridgeId implements Serializable
{
    public Long student_id;

    public Long course_id;

    public int hashCode() {
        return (int)(student_id + course_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        BridgeId b = (BridgeId)obj;
        if(b==null)
            return false;
        if (b.student_id == student_id && b.course_id == course_id) {
            return true;
        }
        return false;
    }
}
