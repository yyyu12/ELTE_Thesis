const table_container = document.querySelector('#table_container');
const pagination = document.querySelector('#pagination');
const pageInput = document.querySelector('#page');

const limit = 8;

async function refresh() {
    const responseArtwork = await fetch('http://localhost:8080/artwork/getAllArtworks');
    const allArtworks = await responseArtwork.json();
    console.log(allArtworks);

    const responseArtist = await fetch('http://localhost:8080/artist/getAllArtists');
    const allArtists = await responseArtist.json();
    console.log(allArtists);

    const artistMap = new Map(allArtists.map(artist => [artist.id, artist]));

    table_container.innerHTML = '';

    allArtworks.forEach((card, index) => {
        if (index < pageInput.value * limit - limit || index > pageInput.value * limit - 1) {
            return;
        }

        // 使用艺术家ID来获取艺术家的详细信息
        const artistInfo = artistMap.get(card.artist_id);
        const artistName = artistInfo ? artistInfo.name : 'Unknown Artist';

        const row = document.createElement('div');
        row.className = 'col-sm-6 col-lg-4 col-xl-3';
        row.innerHTML = `  
            <div class="card shadow h-100">
                <!-- Image -->
                <img src="${card.image_url}" class="card-img-top img-fluid" style="height: 300px; object-fit: cover;" alt="${card.title}">
                <div class="card-body pb-0">
                    <h5 class="card-title artworkTitle fw-normal">${card.title}</h5>
                    <div class="text-muted">${card.description}</div>
                    <p class="mb-2">Artist: ${artistName}</p>
                    <p class="mb-2">Type: ${card.type}</p> 
                    <p class="mb-2">Price: $${card.price.toFixed(2)}</p>
                </div>
                <div class="card-footer pt-0 pb-3">
                    <div class="d-flex justify-content-between mt-2">
                        <button class="btn btn-primary edit-artwork" data-artwork-id="${card.id}" data-bs-toggle="modal" data-bs-target="#artworkEditModal" ">Edit</button>
                        <button class="btn btn-danger delete-artwork" data-artwork-id="${card.id}">Delete</button>
                    </div>
                </div>
            </div>
        `;

        table_container.appendChild(row);
    });

        // 计算当前页的起始和结束索引
        const startIndex = (parseInt(pageInput.value) - 1) * limit;
        const endIndex = startIndex + limit;
    
        // 更新分页导航
        const page_number = Math.ceil(allArtworks.length / limit);
        updatePagination(page_number);
}

function updatePagination(page_number) {
    pagination.innerHTML = '';

    // 创建“上一页”按钮
    const prevLi = document.createElement('li');
    prevLi.className = 'page-item';
    prevLi.innerHTML = '<a class="page-link" href="#" aria-label="Previous" style="color: black;">&laquo;</a>';
    prevLi.onclick = () => {
        if (parseInt(pageInput.value) > 1) {
            pageInput.value = parseInt(pageInput.value) - 1;
            refresh();
        }
    };
    pagination.appendChild(prevLi);

    // 创建页码按钮
    for (let i = 1; i <= page_number; i++) {
        const li = document.createElement('li');
        li.className = 'page-item';
        li.innerHTML = `<a class="page-link" href="#" style="color: black;">${i}</a>`;
        li.onclick = (e) => {
            e.preventDefault();
            pageInput.value = i;
            refresh();
        };
        pagination.appendChild(li);
    }

    // 创建“下一页”按钮
    const nextLi = document.createElement('li');
    nextLi.className = 'page-item';
    nextLi.innerHTML = '<a class="page-link" href="#" aria-label="Next" style="color: black;">&raquo;</a>';
    nextLi.onclick = () => {
        if (parseInt(pageInput.value) < page_number) {
            pageInput.value = parseInt(pageInput.value) + 1;
            refresh();
        }
    };
    pagination.appendChild(nextLi);
}

// 初始加载和搜索输入事件
refresh();


// 添加艺术家品单的显示与隐藏
document.getElementById('showAddArtworkForm').addEventListener('click', function() {
    const artworkForm = document.getElementById('addArtworkForm');
    artworkForm.style.display = artworkForm.style.display === 'block' ? 'none' : 'block';
    this.textContent = artworkForm.style.display === 'block' ? 'Hide Form' : 'Add New Artwork';
});

document.getElementById('artworkForm').addEventListener('submit', async function(e) {
    e.preventDefault(); // 阻止表单默认提交行为

    const formData = {
        artist_id: parseInt(document.getElementById('artist_id').value),
        title: document.getElementById('artworkTitle').value,
        description: document.getElementById('description').value,
        price: parseFloat(document.getElementById('price').value),  
        image_url: document.getElementById('image_url').value,
        type: document.getElementById('type').value
    };
    // 打印出表达数据类型
    console.log(formData);

    fetch('http://localhost:8080/artwork/registerArtwork', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then(response => response.json())
    .then(data => {
        if(data.code === 200){
            alert(data.msg);
            refresh();
        }else{
            alert('Artwork add failed: ' + data.msg + ' - ' + Object.values(data.errors).join(', '));
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Artwork add failed');
    });

    // 清空表单
    this.reset();
});

// 删除艺术品
// 给父元素添加事件监听器，使用事件委托
document.getElementById('table_container').addEventListener('click', function(e) {
    // 检查是否点击的是删除按钮
    if(e.target.classList.contains('btn-danger')){
        const artworkId = e.target.getAttribute('data-artwork-id');
        deleteArtwork(artworkId);
    }
    
});

async function deleteArtwork(artworkId) {
    fetch(`http://localhost:8080/artwork/deleteArtwork/${artworkId}`, {
        method: 'DELETE',
    })
    .then(response => response.json())
    .then(data => {
        if(data.code === 200){
            alert(data.msg);
            refresh();
        }else{
            alert('Artwork delete failed: ' + data.msg);
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Artwork delete failed');
    });
}

// 编辑艺术品modal
document.getElementById('table_container').addEventListener('click', function(e) {
    if(e.target.classList.contains('edit-artwork')){
        const artworkId = e.target.getAttribute('data-artwork-id');
        showEditArtworkModal(artworkId);
    }
});

// 显示编辑艺术品的模态框
const showEditArtworkModal = async (artworkId) => {
    // 异步获取艺术品信息
    const response = await fetch(`http://localhost:8080/artwork/getArtworkById/${artworkId}`);
    const artwork = await response.json();
    console.log(artwork);

    // 填充表单
    document.getElementById('editArtistIdInArtwork').value = artwork.artist_id;
    document.getElementById('editArtworkTitle').value = artwork.title;
    document.getElementById('editDescription').value = artwork.description;
    document.getElementById('editPrice').value = artwork.price;
    document.getElementById('editImageUrl').value = artwork.image_url;
    document.getElementById('editType').value = artwork.type;


    // 存储艺术品ID，以便保存更改时使用
    document.getElementById('editArtworkId').value = artworkId;  // 确保你的模态框中有一个带有 'editArtworkId' id 的隐藏输入字段
};

// 更新艺术品信息
const editArtwork = async () => {
    const artworkId = document.getElementById('editArtworkId').value;  // 获取存储的艺术品ID
    const formData = {
        artist_id: parseInt(document.getElementById('editArtistIdInArtwork').value, 10),
        title: document.getElementById('editArtworkTitle').value,
        description: document.getElementById('editDescription').value,
        price: parseFloat(document.getElementById('editPrice').value),
        image_url: document.getElementById('editImageUrl').value,
        type: document.getElementById('editType').value
    };

    // 异步更新艺术品信息
    try {
        const response = await fetch(`http://localhost:8080/artwork/updateArtwork/${artworkId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });
        const data = await response.json();

        if(data.code === 200){
            alert(data.msg);
            refresh();  // 刷新艺术品列表
        } else {
            alert('Artwork update failed: ' + data.msg + ' - ' + Object.values(data.errors).join(', '));
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Artwork update failed');
    }
};

// 绑定保存按钮事件
document.getElementById('saveArtworkChanges').addEventListener('click', editArtwork);
