var express = require('express');
var router = express.Router();
var db = require('../database-module/database.js'); // mysql 연동 

router.get('/', function (req, res, next) {
    
        db.getPool().getConnection(function (err, connection) {
            
            
            // Use the connection
            connection.query('SELECT * FROM board limit 1', function (err, rows) {
                if (err) console.error("err : " + err);
                console.log("rows : " + JSON.stringify(rows));
                res.render('index', {title: 'test', rows: rows});
                connection.release();
                console.log("종료 확인");
                // Don't use the connection here, it has been returned to the pool.
            });
    
    
        });
    });

/* GET home page. */
router.get('/get', function (req, res, next) {

    db.getPool().getConnection(function (err, connection) {
        
        
        // Use the connection
        connection.query('SELECT * FROM board limit 1', function (err, rows) {
            if (err) console.error("err : " + err);
            console.log("rows : " + JSON.stringify(rows));
            res.json({rows: rows});
            connection.release();
            console.log("종료 확인");
            // Don't use the connection here, it has been returned to the pool.
        });


    });
});

module.exports = router;
