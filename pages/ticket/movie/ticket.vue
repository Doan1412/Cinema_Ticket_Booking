<template>
    <div class="cnt3">
        <div class="cntt">
            <div v-for="seatId in listSeat" :key="seatId">
                <Ticket :seatId="seatId" :showId="showId" :movieId="movieId" />
            </div>
        </div>
        <button type="submit"
            class="text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mb-2 left2"
            @click="submitt">
            Confirm
        </button>
        <Notification :status="status" :object="'tài khoản'" :action="'Đăng nhập'" :isShowNoti="showNoti"
            v-if="showNoti == 'Ok'">
        </Notification>
    </div>
</template>
<script>
import Credit from '~/components/Credit.vue';
import Ticket from '~/components/Ticket.vue';
import PopupConfirm from '~/components/PopupConfirm.vue'
export default {
    props: {
        listSeat: {
            type: Array
        },
        showId: {
            type: Number,
            default: 0
        },
        movieId: {
            type: String,
            default: ""
        }
    },
    data() {
        return {
            isShowPopup: false,
            show: null,
            booking: {},
        };
    },
    async fetch() {
        try {
            const responseShow = await this.$axios.get(`http://localhost:8080/api/show/id=${this.showId}`);
            this.show = responseShow.data;
        }
        catch (error) {
            console.log(error);
        }
    },
    methods: {
        closePopup() {
            this.isShowPopup = false;
        },
        async submitt() {
            this.booking.showId = this.showId;
            this.booking.username = localStorage.getItem('username');
            this.booking.createOn = new Date().toISOString();
            this.booking.listSeatId = this.listSeat;
            console.log(JSON.stringify(this.booking));
            try {
                console.log(this.booking);
                await this.$axios.post("http://localhost:8080/api/booking/create", this.booking).then((response) => {
                    this.status = "thành công";
                    this.showNoti = "Ok";
                    setTimeout(() => {
                        this.showNoti = "";
                    }, 1500);
                })
            }
            catch (error) {
                console.log(error);
                this.status = "thất bại";
                this.showNoti = "Ok";
                setTimeout(() => {
                    this.showNoti = "";
                }, 1500);
            }
        }
    },
    components: { Credit }
}
</script>
<style>
.ctn3 {
    margin-top: 20px;
    width: 1000px;
}

.cntt {
    margin: -80px auto 20px 0;
    /* margin-left: 50px; */
}

.block {
    display: block;
    margin-left: 50px;
}

.left2 {
    margin-top: 20px;
    margin-left: 700px;
}
</style>