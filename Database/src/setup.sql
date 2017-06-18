create table if not exists WindowedMetrics (
	windowed_metric_id bigint unsigned not null primary key auto_increment,
	owner_id varchar(200) not null,
    sensor_type varchar(200) not null,
    window_size int unsigned not null,
    window_start bigint unsigned not null,
    sensor_avg double not null,
    sensor_min double not null, 
    sensor_max double not null,
    sensor_count bigint unsigned not null,
    
    unique index (owner_id, sensor_type, window_size, window_start)
);

create table if not exists Alerts (
	alert_id bigint unsigned not null primary key auto_increment,
	owner_id varchar(200) not null,
	sensor_id varchar(200) not null,
	sensor_type varchar(200) not null,
	threshold_type varchar(200) not null,
	lower_bound double not null,
	upper_bound double not null,
	sensor_data double not null,
	sensor_timestamp long not null,
	is_read bit not null default 0
);