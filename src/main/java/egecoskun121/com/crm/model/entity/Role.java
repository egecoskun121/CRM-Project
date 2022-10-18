package egecoskun121.com.crm.model.entity;



public enum Role  {
    ROLE_ADMIN, ROLE_CLIENT;

    public String getAuthority() {
        return name();
    }
}
