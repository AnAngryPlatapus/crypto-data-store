db.createUser(
    {
        user: "cyber",
        pwd: "ur_mums_security",
        roles: [
            {
                role: "readWrite",
                db: "crypto-data-store"
            }
        ]
    }
);