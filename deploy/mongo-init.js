db.createUser(
    {
        user: "smiller",
        pwd: "A14worldpeace",
        roles: [
            {
                role: "readWrite",
                db: "crypto-data-store"
            }
        ]
    }
);