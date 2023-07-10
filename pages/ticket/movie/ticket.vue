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
            console.log(this.$data.show.id + " " + this.listSeat);
            try {
                // await this.$axios.$post.
            }
            catch (error) {
                console.log(error);
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