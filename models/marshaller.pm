ctmc

// Maximum queue size
const int q_max = 20;

// Request arrival rate

const double rate_arrive = 0.9;
const double rate_marshaller1 = 1/0.1102;
const double rate_marshaller2 = rate_marshaller1*5;

module Environment
	
	qsize : [0..q_max] init 0;
    nl : [0..200] init 0;

    [invP] (true) & ((qsize+1)>q_max) -> (nl'= min(nl+1,200));
	[invP] (true) & ((qsize+1)<=q_max) -> rate_arrive : (qsize'=min(qsize+1,q_max));
	[process] qsize>1 -> (qsize'=qsize-1);
	[process_last] qsize=1 -> (qsize'=qsize-1);
	
endmodule

module Marshaller1
	sp1 : [0..2] init 1;
	
        [invP] sp1=1 -> (sp1'=2);
        [invP] sp1=2 -> (sp1'=sp1);

	[process]     (m_id=0)&(sp1=2) -> rate_marshaller1 : (sp1'=2);
	[process_last](m_id=0)&(sp1=2) -> rate_marshaller1 : (sp1'=1); 

	[process]     (m_id=1)&(sp1=2) -> rate_marshaller2 : (sp1'=2);
	[process_last](m_id=1)&(sp1=2) -> rate_marshaller2 : (sp1'=1); 
	
endmodule

const int q_trigger;

module AdaptationManager
	m_id : [0..1] init 0;

	[m1tom2] (qsize >= q_trigger)&(m_id=0) -> 100000 : (m_id'=1);
	[m2tom1] (qsize < q_trigger)&(m_id=1)  -> 100000 : (m_id'=0);
	
endmodule

rewards "queue_size"
	true : qsize;
endrewards

rewards "lost_requests"
        true : nl;
endrewards