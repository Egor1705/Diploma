'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var singleStudUploadForm = document.querySelector('#singleStudUploadForm');
var singleStudFileUploadInput = document.querySelector('#singleStudFileUploadInput');
var singleStudFileUploadError = document.querySelector('#singleStudFileUploadError');
var singleStudFileUploadSuccess = document.querySelector('#singleStudFileUploadSuccess');


function uploadSingleFile(file,deadL,subj_id) {
    var formData = new FormData();
  //  var formDesc = new FormData();
    formData.append("file", file);
    formData.append("subject_id", subj_id);
    formData.append("deadL", deadL.toUTCString());
 //   formDesc.append("description", description);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/subject/uploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
          //  singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
 //           singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
   // xhr.send(formDesc);
}

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);



function myFunction() {
	  alert("Hello! I am an alert box!");
	}




function uploadSingleStudFile(file,user_id,id_task) {
    var formData = new FormData();
  //  var formDesc = new FormData();
    formData.append("file", file);
    formData.append("user_id", user_id);
    formData.append("id_task", id_task);
 //   formDesc.append("description", description);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadStudFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleStudFileUploadError.style.display = "none";
          //  singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
 //           singleFileUploadSuccess.style.display = "block";
        } else {
            singleStudFileUploadSuccess.style.display = "none";
            singleStudFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
   // xhr.send(formDesc);
}

singleStudUploadForm.addEventListener('submit', function(event){
    var files = singleStudFileUploadInput.files;
    if(files.length === 0) {
        singleStudFileUploadError.innerHTML = "Please select a file";
        singleStudFileUploadError.style.display = "block";
    }
    uploadSingleStudFile(files[0]);
    event.preventDefault();
}, true);


