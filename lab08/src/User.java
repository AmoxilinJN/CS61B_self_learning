import java.util.Arrays;
import java.util.Objects;

/** Represents an example of how a website might model a user. */
public class User implements Comparable<User> {
    /* TODO: Make Users a comparable type.
        Use parameterization (ie. <>) to ensure that User can only be
        used to compare against other Users. */
    private static int nextId = 1;

    private static final int AGE_MODULUS = 13;
    private static final int AGE_CONSTANT = 20;

    private int id;
    private int age;
    private String name;
    private String email;

    public User(String name, String email) {
        this(nextId, name, email);
        nextId += 1;
    }

    /** Force assign an id to a created user **/
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        setAge();
    }

    /** For this assignment, age is just an automatically assigned field. */
    void setAge() {
        age = (id % AGE_MODULUS) + AGE_CONSTANT;
    }

    int getAge() {
        return age;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + "}";
    }

    /** Returns whether or not two Users are considered equal to each other. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User other = (User) o;
        if (id != other.id) {
            return false;
        } else if (!Objects.equals(name, other.name)) {
            return false;
        } else {
            return Objects.equals(email, other.email);
        }
    }

    public static void main(String[] args) {
        User[] users = {
                new User(2, "Noah", ""),
                new User(4, "Wilson", ""),
                new User(5, "Karen", ""),
                new User(1, "Yinqi", ""),
                new User(1, "Amy", "")
        };
        Arrays.sort(users);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Override
    public int compareTo(User o) {
        if(this.id == o.id){
            char[] arr1=this.name.toCharArray();
            char[] arr2=o.name.toCharArray();
            for(int i=0;i<arr1.length&&i<arr2.length;i++){
                if (arr1[i]==arr2[i]){
                    continue;
                }else{
                    return arr1[i]-arr2[i];
                }
            }
        }
        return this.id-o.id;
    }
}
