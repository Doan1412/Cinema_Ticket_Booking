<template>
    <div>
        <Ticket :seatId="seatId" :showId="showId" :movieId="movieId"/>
    </div>
</template>
<script>
import Ticket from '~/components/Ticket.vue';
export default {
    data(){
        return {
            movieId:1,
            seatId: 1,
            showId: 3,
        }
    }
}
</script>