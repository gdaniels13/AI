var fs = require('fs')

var fileName = "modifiedGenerated.txt";
 

 function parse(data)
 {
  console.log("#rollouts\truleMCTSdev\trulebaseddev\tmctsMedian\trulebasedMedian")
    var line="";
    // console.log(data);
    for(var i = 2; i<=data.length; i+=30)
    {
      var first =[];
      var firstMedian =0;
      var second =[];
      var secondMedian = 0;
      
      for(var j = 0; j<30; ++j)
      {
        line = data[i+j].split('\t');
        first.push(parseInt(line[1]));
        firstMedian += first[j];
        second.push(parseInt(line[2]));
        secondMedian +=second[j];
      }
      firstMedian/=30;
      secondMedian/=30;

      var firstx=0;
      var secondx=0;
      for(var j = 0; j<30; ++j)
      {
        secondx +=Math.pow(second[j]-secondMedian,2);
        firstx +=Math.pow(first[j]-firstMedian,2);
      }
      secondx = Math.sqrt(secondx/29);
      firstx = Math.sqrt(firstx/29);

      console.log(line[0] + '\t' + firstx + '\t' + secondx + '\t' + 
        firstMedian + '\t' + secondMedian );

    }
 }




fs.exists(fileName, function(exists) {
  if (exists) {
    fs.stat(fileName, function(error, stats) {
      fs.open(fileName, "r", function(error, fd) {
        var buffer = new Buffer(stats.size);
 
        fs.read(fd, buffer, 0, buffer.length, null, function(error, bytesRead, buffer) {
          var data = buffer.toString("utf8", 0, buffer.length);
 
          parse(data.split('\n'));
          fs.close(fd);
        });
      });
    });
  }
});
