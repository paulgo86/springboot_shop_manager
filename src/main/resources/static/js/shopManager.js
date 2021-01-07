// image processing
/*
    - add image
    - remove image            
    - set image files array
    - setPreview
*/
	const imageFileManager = {
	    list_images:[],
	    content_images:[],
	    flag:'list_images',
	    addImage:(files)=>{     
	        let type = imageFileManager.flag;           
	        for(let f of files){                    
	            imageFileManager[type].push(f);
	        }
	        console.log(imageFileManager[type]);
	        imageFileManager.setPreview(
	            type,
	            imageFileManager[type]
	        );
	    },
	    removeImage:(type,idx)=>{
	        imageFileManager[type].splice(idx,1);
	        imageFileManager.setPreview(type);
	    },
	    setPreview:(type)=>{
	        // init previews by type
	        let files = imageFileManager[type];
	        let previewContainerIdx = type === 'list_images'?0:1;
	        let wrapper = $('.cardWrapper').eq(previewContainerIdx);
	        wrapper.html('');
	
	        // set cards to preview container
	        
	        for(let i=0; i<files.length; i++){
	            let card = imageFileManager.getCard(type,i,files[i].name,files[i]);
	            card.appendTo(wrapper);
	        }
	    },
	    getCard:(type,idx,name,file)=>{                
	        /*
	        div.card 
	            > img.card-img-top
	            > div.card-body
	                > h5
	            > button.btn btn-danger removeBtn
	        */
	        
	        // card
	        let card = $('<div/>')
	            .addClass('card')
	        // img
	        $('<img/>')
	            .addClass('card-img-top')
	            .prop('src',URL.createObjectURL(file))
	            .appendTo(card);
	        // card-body
	        let cardBody = $('<div/>')
	            .addClass('card-body')
	            .appendTo(card);
	            // card-title
	        $('<h5/>')
	            .addClass('card-title')
	            .text(name)
	            .appendTo(cardBody);
	        // button
	        $('<button/>')
	            .addClass('btn btn-danger removeBtn')
	            .prop('type','button')
	            .text('X')
	            .click(()=>{
	                imageFileManager.removeImage(type,idx)
	            })
	            .appendTo(card);
	        return card;
	    }
	}
	
	$('#add_list_image').click(()=>{
	        console.log('add list image clicked !');
	        imageFileManager.flag = 'list_images';
	        $('#fileInput').trigger("click");
	    }
	);
	
	$('#add_content_image').click(()=>{
	        console.log('add content image clicked !');
	        imageFileManager.flag = 'content_images';
	        $('#fileInput').trigger("click");
	    }
	);
	
	// file manage with fileInput
	$('#fileInput').change((e)=>{
	    console.log(e.target.files);
	    imageFileManager.addImage(e.target.files);
	});

        
	// add manager
	$('#manager_add').click(()=>{
	    // check id and name
	    let id = $('#m_id').val();
	    let name = $('#m_name').val();
	    if(!(id && name)){
	        return alert("아이디와 이름을 입력하세요.");
	    }
	
		/*
		    let formData = new FormData();
		    let request = new XMLHttpRequest();
		
		    formData.append('id',id);
		    formData.append('name',name);
		
		    request.open('POST','/manager/addManager');
		    request.send(formData);
		*/
		$('#managerAddForm').submit();
	    
	});
	
	$('.removeManager').click((e)=>{
		let q = e.target.name+'번 매니저를 삭제하시겠습니까?';
		if(confirm(q)){
			console.log('/manager/deleteManager?idx='+e.target.name);
			window.location.href='/manager/deleteManager?idx='+e.target.name;
		}
	});
        