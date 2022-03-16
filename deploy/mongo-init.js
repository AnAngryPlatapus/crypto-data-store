const p = "TODO code to grab jvm password as runtime arg"

db.createUser(
    {
        user: "smiller",
        pwd: p,
        roles: [
            {
                role: "readWrite",
                db: "crypto-data-store"
            }
        ]
    }
);