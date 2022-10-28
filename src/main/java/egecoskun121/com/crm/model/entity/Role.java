package egecoskun121.com.crm.model.entity;



public enum Role  {
    ROLE_ADMIN, ROLE_USER;

    public String getAuthority() {
        return name();
    }
}
