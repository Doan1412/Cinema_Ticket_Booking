<template>
    <div class="2xl:container md:mx-auto flex flex-row bg-gray-50">
        <div class="basis-1/5">
            <AdminSidebar />
        </div>
        <div class="class-list basis-5/6 mt-10 bg-gray-50">
            <h2 class="app-title">List show</h2>
            <a href="/admin/show/add">
                <button
                    class="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-green-400 to-blue-600 group-hover:from-green-400 group-hover:to-blue-600 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800">
                    <span
                        class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        Add show
                    </span>
                </button>
            </a>
            <ul class="responsive-table">
                <li class="table-header">
                    <div class="col col-0">ID</div>
                    <div class="col col-1">Movie</div>
                    <div class="col col-2">Cinema Hall</div>
                    <div class="col col-3">Duration</div>
                    <div class="col col-4">Start</div>
                    <div class="col col-5">Capacity</div>
                </li>
            </ul>
            <ul class="responsive-table content" v-for="(item, index) in items" :key="index">
                <li class="table-row display">
                    <div class="col col-0" data-label="ID">{{ item?.id }}</div>
                    <div class="col col-1" data-label="Movie">
                        {{ item.movie.title }}
                    </div>
                    <div class="col col-2" data-label="CineHall">{{ item.cinemaHall.name }}</div>
                    <div class="col col-3" data-label="Giới tính">{{ item.duration }}</div>
                    <div class="col col-4" data-label="Số điện thoại">
                        {{ new Date(item.startTime).toLocaleTimeString() + " " + new
                            Date(item.startTime).toLocaleDateString()
                        }}
                    </div>
                    <div class="col col-5" data-label="capacity">{{ item?.cinemaHall.capacity }}</div>
                    <div class="status col col-6">
                        <svg class="icon__status-dot" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 128 512">
                            <path
                                d="M56 472a56 56 0 1 1 0-112 56 56 0 1 1 0 112zm0-160a56 56 0 1 1 0-112 56 56 0 1 1 0 112zM0 96a56 56 0 1 1 112 0A56 56 0 1 1 0 96z" />
                        </svg>
                        <ul class="update-app">
                            <li @click.prevent="deleteShow(item)" class="deny-status">
                                Xóa
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>
<script>
import AdminSidebar from '~/components/AdminSidebar.vue';
export default {
    data() {
        return {
            items: [],
            isShowPopupDelete: false,
            status: "",
            showNoti: "",
            updatedStatus: "",
            isShowPopup: ""
        };
    },
    async fetch() {
        await this.$axios.get("http://localhost:8080/api/show/all").then((response) => {
            this.items = response.data;
            // console.log(this.items);
        });
    },
    methods: {
        closePopup() {
            this.isShowPopupDelete = false;
            this.isShowPopup = false;
        },
        async deleteShow(item) {
            try {
                await this.$axios.delete(`http://localhost:8080/api/show/delete/id=${item.id}`).then((res) => {
                    const index = this.items.indexOf(item);
                    if (index > -1) {
                        this.items.splice(index, 1); // Xóa phần tử khỏi mảng this.films
                    }
                    this.status = "thành công";
                    this.showNoti = "Ok";
                    this.action = "Delete";
                    setTimeout(() => {
                        this.showNoti = "";
                    }, 1500);
                });
            }
            catch (error) {
                console.log(error);
            }
        }
    },
    components: { AdminSidebar }
}
</script>
<style>
h2 {
    font-size: 26px;
    text-align: center;
}


.responsive-table .col-0 {
    flex-basis: 10%;
}

.responsive-table .col-1 {
    flex-basis: 25%;
}

.responsive-table .col-2 {
    flex-basis: 10%;
}

.responsive-table .col-3 {
    flex-basis: 10%;
}

.responsive-table .col-4 {
    flex-basis: 20%;
}

.responsive-table .col-6 {
    flex-basis: 10%;
}

.responsive-table .col-5 {
    flex-basis: 10%;
    padding-right: 50px;
}


ul {
    list-style: none;
}

.responsive-table {
    padding-right: 0;
}

.div-center {
    display: flex;
    text-align: center;
    justify-content: flex-start;
    align-items: center;
}

.responsive-table li {
    border-radius: 3px;
    padding: 25px 30px;
    display: flex;
    justify-content: space-between;
    margin-bottom: 25px;
}

.responsive-table .table-header {
    background-color: rgb(159, 188, 159);
    font-size: 14px;
    text-transform: uppercase;
    letter-spacing: 0.03em;
}

.responsive-table .table-row {
    background-color: #ffffff;
    box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.1);
    position: relative;
    width: 1155px;
}

.responsive-table div {
    text-align: center;
    cursor: pointer;
}

.status {
    position: absolute;
    right: 0px;
    top: 15px;
}

.icon__status-dot {
    position: absolute;
    right: 0px;
    top: 10px;
    width: 3px !important;
    height: auto;
    cursor: pointer;
    z-index: 3;
    /* z-index: 4; */
}

.icon__status-dot:hover {
    fill: green;
}

.icon__status-dot:hover+.update-app {
    opacity: 1;
    visibility: visible;
    display: block;
}

.status-action {
    padding: 10px 15px;
    box-shadow: 3px 3px 10px #cccccc;
    background-color: #fff;
    border-radius: 5px;
    width: 100px;
    font-size: 15px;
    z-index: 3;
    transition: all 0.2s linear;
    opacity: 0;
    visibility: hidden;
}

.status-action li {
    padding: 0;
    margin: 0;
    cursor: pointer;
}

.status-action li:hover {
    color: rgb(196, 152, 20);
    font-weight: 550;
}

.status-action .accept-status:hover {
    color: #127E23;
    font-weight: 550;
}

.status-action .deny-status:hover {
    color: rgb(196, 29, 20);
    font-weight: 550;
}

.icon__status-dot:hover+.status-action {
    opacity: 1;
    visibility: visible;
    display: block;
}

.status-action:hover {
    opacity: 1;
    visibility: visible;
    display: block;
}

.update-app {
    padding: 10px 15px;
    box-shadow: 3px 3px 10px #cccccc;
    background-color: #fff;
    border-radius: 5px;
    width: 100px;
    font-size: 15px;
    z-index: 3;
    transition: all 0.2s linear;
    opacity: 0;
    visibility: hidden;
}

.update-app:hover {
    opacity: 1;
    visibility: visible;
    display: block;
}

.update-app li {
    padding: 0;
    margin: 0;
    cursor: pointer;
}

.update-app li:hover {
    color: rgb(196, 152, 20);
    font-weight: 550;
}

.update-app .accept-status:hover {
    color: #127e23;
    font-weight: 550;
}

.update-app .deny-status:hover {
    color: rgb(196, 29, 20);
    font-weight: 550;
}
</style>