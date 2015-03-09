--
-- Clean up schema
--
DROP TRIGGER IF EXISTS `messages_update`;
DROP TRIGGER IF EXISTS `messages_insert`;
DROP TRIGGER IF EXISTS `subscriptions_update`;
DROP TRIGGER IF EXISTS `subscriptions_insert`;
DROP TRIGGER IF EXISTS `hash_tags_update`;
DROP TRIGGER IF EXISTS `hash_tags_insert`;
DROP TRIGGER IF EXISTS `user_preferences_update`;
DROP TRIGGER IF EXISTS `user_preferences_insert`;
DROP TRIGGER IF EXISTS `users_update`;
DROP TRIGGER IF EXISTS `users_insert`;

DROP TABLE IF EXISTS `public_messages`;
DROP TABLE IF EXISTS `private_messages`;
DROP TABLE IF EXISTS `messages`;
DROP TABLE IF EXISTS `subscriptions`;
DROP TABLE IF EXISTS `hash_tags`;
DROP TABLE IF EXISTS `user_preferences`;
DROP TABLE IF EXISTS `users`;


--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT,
  `fb_profile_id` bigint(64) unsigned NOT NULL,
  `screen_name` varchar(128) default NULL,
  `last_activity` timestamp NULL default NULL,
  `last_lat` decimal(12,8) default NULL,
  `last_lon` decimal(12,8) default NULL,
  `ins_user` varchar(12) default NULL,
  `ins_ts` timestamp NULL default NULL,
  `upd_user` varchar(12) default NULL,
  `upd_ts` timestamp NULL default NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TRIGGER `users_insert` BEFORE INSERT ON `users` 
FOR EACH ROW 
	SET NEW.ins_ts = NOW(), 
		NEW.ins_user = SUBSTRING_INDEX(USER(),'@',1);
 
CREATE TRIGGER `users_update` BEFORE UPDATE ON `users` 
FOR EACH ROW 
	SET NEW.upd_ts = NOW(), 
		NEW.ins_ts = OLD.ins_ts, 
		NEW.upd_user = SUBSTRING_INDEX(USER(), '@',1), 
		NEW.ins_user = OLD.ins_user;

		
--
-- Table structure for table `user_preferences`
--
    
CREATE TABLE `user_preferences` (
  `user_id` int(16) NOT NULL,
  `broadcast_loc` tinyint(1) NOT NULL,
  `show_profile_pic` tinyint(1) NOT NULL,
  `ins_user` varchar(12) default NULL,
  `ins_ts` timestamp NULL default NULL,
  `upd_user` varchar(12) default NULL,
  `upd_ts` timestamp NULL default NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `user_preferences_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TRIGGER `user_preferences_insert` BEFORE INSERT ON `user_preferences` 
FOR EACH ROW 
	SET NEW.ins_ts = NOW(), 
		NEW.ins_user = SUBSTRING_INDEX(USER(),'@',1);
 
CREATE TRIGGER `user_preferences_update` BEFORE UPDATE ON `user_preferences` 
FOR EACH ROW 
	SET NEW.upd_ts = NOW(), 
		NEW.ins_ts = OLD.ins_ts, 
		NEW.upd_user = SUBSTRING_INDEX(USER(), '@',1), 
		NEW.ins_user = OLD.ins_user;

		
--
-- Table structure for table `hash_tags`
--
    
CREATE TABLE `hash_tags` (
  `hash_tag_id` int(16) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(256) NOT NULL,
  `ins_user` varchar(12) default NULL,
  `ins_ts` timestamp NULL default NULL,
  `upd_user` varchar(12) default NULL,
  `upd_ts` timestamp NULL default NULL,
  PRIMARY KEY  (`hash_tag_id`),
  UNIQUE KEY `hash_tag_id` (`hash_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TRIGGER `hash_tags_insert` BEFORE INSERT ON `hash_tags` 
FOR EACH ROW 
	SET NEW.ins_ts = NOW(), 
		NEW.ins_user = SUBSTRING_INDEX(USER(),'@',1);
 
CREATE TRIGGER `hash_tags_update` BEFORE UPDATE ON `hash_tags` 
FOR EACH ROW 
	SET NEW.upd_ts = NOW(), 
		NEW.ins_ts = OLD.ins_ts, 
		NEW.upd_user = SUBSTRING_INDEX(USER(), '@',1), 
		NEW.ins_user = OLD.ins_user;


--
-- Table structure for table `subscriptions`
--

CREATE TABLE `subscriptions` (
  `subscription_id` int(16) NOT NULL AUTO_INCREMENT,
  `renewal_ts` timestamp NOT NULL,
  `user_id` int(16) NOT NULL,
  `hash_tag_id` int(16) NOT NULL,
  `subscription_lat` decimal(12,8) NOT NULL,
  `subscription_lon` decimal(12,8) NOT NULL,
  `ins_user` varchar(12) default NULL,
  `ins_ts` timestamp NULL default NULL,
  `upd_user` varchar(12) default NULL,
  `upd_ts` timestamp NULL default NULL,
  PRIMARY KEY  (`subscription_id`),
  UNIQUE KEY `subscription_id` (`subscription_id`),
  KEY `user_id` (`user_id`),
  KEY `hash_tag_id` (`hash_tag_id`),
  CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `subscriptions_ibfk_2` FOREIGN KEY (`hash_tag_id`) REFERENCES `hash_tags` (`hash_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TRIGGER `subscriptions_insert` BEFORE INSERT ON `subscriptions` 
FOR EACH ROW 
	SET NEW.ins_ts = NOW(), 
		NEW.ins_user = SUBSTRING_INDEX(USER(),'@',1);
 
CREATE TRIGGER `subscriptions_update` BEFORE UPDATE ON `subscriptions` 
FOR EACH ROW 
	SET NEW.upd_ts = NOW(), 
		NEW.ins_ts = OLD.ins_ts, 
		NEW.upd_user = SUBSTRING_INDEX(USER(), '@',1), 
		NEW.ins_user = OLD.ins_user;

		
--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `message_id` int(16) NOT NULL AUTO_INCREMENT,
  `origin_user_id` int(16) NOT NULL,
  `message_type` varchar(7) NOT NULL,
  `ins_user` varchar(12) default NULL,
  `ins_ts` timestamp NULL default NULL,
  `upd_user` varchar(12) default NULL,
  `upd_ts` timestamp NULL default NULL,
  PRIMARY KEY  (`message_id`),
  UNIQUE KEY `message_id` (`message_id`),
  KEY `origin_user_id` (`origin_user_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`origin_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TRIGGER `messages_insert` BEFORE INSERT ON `messages` 
FOR EACH ROW 
	SET NEW.ins_ts = NOW(), 
		NEW.ins_user = SUBSTRING_INDEX(USER(),'@',1);
 
CREATE TRIGGER `messages_update` BEFORE UPDATE ON `messages` 
FOR EACH ROW 
	SET NEW.upd_ts = NOW(), 
		NEW.ins_ts = OLD.ins_ts, 
		NEW.upd_user = SUBSTRING_INDEX(USER(), '@',1), 
		NEW.ins_user = OLD.ins_user;

--
-- Table structure for table `private_messages`
--

CREATE TABLE `private_messages` (
  `message_id` int(16) NOT NULL,
  `destination_user_id` int(16) NOT NULL,
  PRIMARY KEY  (`message_id`),
  UNIQUE KEY `message_id` (`message_id`),
  KEY `destination_user_id` (`destination_user_id`),
  CONSTRAINT `private_messages_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `messages` (`message_id`),
  CONSTRAINT `private_messages_ibfk_2` FOREIGN KEY (`destination_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `public_messages`
--

CREATE TABLE `public_messages` (
  `message_id` int(16) NOT NULL,
  `destination_hash_tag_id` int(16) NOT NULL,
  `message_lat` decimal(12,8) default NULL,
  `message_lon` decimal(12,8) default NULL,
  PRIMARY KEY  (`message_id`),
  UNIQUE KEY `message_id` (`message_id`),
  KEY `destination_hash_tag_id` (`destination_hash_tag_id`),
  CONSTRAINT `public_messages_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `messages` (`message_id`),
  CONSTRAINT `public_messages_ibfk_2` FOREIGN KEY (`destination_hash_tag_id`) REFERENCES `hash_tags` (`hash_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;