package test1

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority:'ROLE_ADMIN').save()

        def me = new User(username:'thedesignwiz@gmail.com', password:'123456').save()

        UserRole.create me, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
