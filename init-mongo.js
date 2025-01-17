db = db.getSiblingDB('admin');
db.createUser({
    user: "root",
    pwd: "secret",
    roles: [{ role: "root", db: "admin" }]
});

db = db.getSiblingDB('mydatabase');
db.createUser({
    user: "root",
    pwd: "secret",
    roles: [{ role: "readWrite", db: "mydatabase" }]
});
