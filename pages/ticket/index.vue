<template>
    <div>
        <div class="2xl:container md:mx-auto flex flex-row bg-gray-50">
            <div class="basis-1/5 head">
                <Sidebar />
            </div>
            <div class="basis-5/6 mt-10 left">
                <div class="head">
                    <Headerbar />
                </div>
                <div class="mt-10 ticket">
                    <h1 class="text-4xl">History</h1>
                    <!-- <hr style="width:100%;text-align:left;margin-left:0;margin-top: 40px;height:3px;"> -->
                    <!-- <br> -->
                    <div v-for="booking in bookings" :key="booking.id">
                        <div v-for="seatId in booking.listSeatId" :key="seatId">
                            <Ticket :seatId="seatId" :showId="booking.showId" :movieId="booking.movie" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Sidebar from '~/components/Sidebar.vue';
import Ticket from '~/components/Ticket.vue';
export default {
    data() {
        return {
            bookings: [],
            username: null,
        };
    },
    mounted() {
        this.username = localStorage.getItem("username");
        this.getBookings();
    },
    methods: {
        async getBookings() {
            console.log(this.username);
            await this.$axios.get("http://localhost:8080/api/booking/username=" + this.username).then((response) => {
                {
                    this.bookings = response.data;
                }
            });
            console.log(this.bookings);
        }
    },
    components: { Sidebar }
}
</script>
<style>
.left {
    margin-left: 300px;
}

.head {
    height: 50px;
    width: 1000px;
}
.ticket {
    margin-left:100px;
}
</style>