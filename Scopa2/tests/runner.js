
var exec = require('child_process').exec;


var nodemailer = require('nodemailer');
var smtpTransport = nodemailer.createTransport("SMTP",{
    service: "Gmail",
    auth: {
        user: "zombieattackeditor@gmail.com",
        pass: "eatbrains"
    }
});

exec('java -jar improved.jar random' ,function(a,b,c){
	sendSuccessRequestEmail("improved  is finished",a,b,c)
});
pausecomp(60000);
exec('java -jar improved.jar ruleBased' ,function(a,b,c){
	sendSuccessRequestEmail("RuleBased is finished",a,b,c)
});

function pausecomp(millis)
 {
  var date = new Date();
  var curDate = null;
  do { curDate = new Date(); }
  while(curDate-date < millis);
}


function sendSuccessRequestEmail( data,a,b,c){
    var mailOptions= {
        from: "Zombie Attack <zombieattackeditor@gmail.com>",
        to: "gdaniels13@gmail.com",
        subject:"Completeion",
        text: [data ,a,b,c]
    };

    smtpTransport.sendMail(mailOptions, function(error, response) {
        if(error)
        {
            console.log("failed to send email" + error);
        }
        else
        {
            console.log("email sent successfully" + error);   
        }
    });
}
